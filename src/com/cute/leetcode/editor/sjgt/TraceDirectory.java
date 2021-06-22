package com.cute.leetcode.editor.sjgt;

import java.util.*;

/**
 * @program: 世纪高通 遍历文件夹
 * @description:
 * @author: lgy
 * @create: 2021-06-22 14:54
 **/
/*在一个程序中，同时遍历D:\盘与E:\盘下文件夹与文件（包含子文件和子文件夹
并进行打印，如果一个优先遍历完成，则停止全部遍历，
并记录遍历时间*/
public class TraceDirectory {


    /**
     * 目录类
     * childDir 子目录
     * dirName 目录名
     * files 文件
     */
    static class Directory {
        private Set<Directory> childDir;

        private String dirName;

        private Set<File> files;


        public Directory() {
        }


        public Directory(String dirName) {
            this.dirName = dirName;
            childDir = new HashSet<>();
            files = new HashSet<>();
        }

        public Directory(Set<Directory> childDir, String dirName, Set<File> files) {
            this.childDir = childDir;
            this.dirName = dirName;
            this.files = files;
        }

        public Set<Directory> getChildDir() {
            return childDir;
        }

        public void setChildDir(Set<Directory> childDir) {
            this.childDir = childDir;
        }

        public Set<File> getFiles() {
            return files;
        }

        public void setFiles(Set<File> files) {
            this.files = files;
        }

        public String getDirName() {
            return dirName;
        }

        public void setDirName(String dirName) {
            this.dirName = dirName;
        }
    }

    /**
     * 文件类
     * fileName 文件名
     * type 文件类型
     */
    static class File {
        private String fileName;

        private String type;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public File(String fileName, String type) {
            this.fileName = fileName;
            this.type = type;
        }
    }

    //思路一：volatile关键字，作为线程共享变量
    //通过观测END_FLAG标识位判断是否终止线程
    //与此同时，使用interrupt()方法进行线程终止
    //本机为mac,只能模拟一下文件路径和文件名了
    //具体代码如下
    private static volatile boolean END_FLAG = false;

    public static void main(String[] args) {
        Directory d = new Directory("D");

        Directory childD = new Directory("childD");
        //D盘文件（21个文件夹+childD 里 100个txt文件）
        for (int i = 0; i < 3000; i++) {
            childD.files.add(new File(UUID.randomUUID().toString(), "txt"));
        }
        d.childDir.add(childD);
        for (int i = 0; i < 20; i++) {
            d.childDir.add(new Directory(UUID.randomUUID().toString()));
        }
        Directory e = new Directory("E");
        Directory childE = new Directory("childE");
        for (int i = 0; i < 4000; i++) {
            childE.files.add(new File(UUID.randomUUID().toString(), "jpeg"));
        }
        e.childDir.add(childE);
        //E盘51个文件夹 childE有200个jpeg文件
        for (int i = 0; i < 50; i++) {
            e.childDir.add(new Directory(UUID.randomUUID().toString()));
        }
        TraceThread traceD = new TraceThread(d);
        TraceThread traceE = new TraceThread(e);
        traceD.start();
        traceE.start();


    }

    static class TraceThread extends Thread {

        private Long beginTime;

        private Long endTime;

        public TraceThread(Directory directory) {
            super();
            this.directory = directory;
        }

        Directory directory;

        public Directory getDirectory() {
            return directory;
        }

        public void setDirectory(Directory directory) {
            this.directory = directory;
        }

        @Override
        public void run() {
            beginTime= System.currentTimeMillis();
            System.out.println("开始扫描当前文件夹" + Thread.currentThread().getName() + "开始时间：" + beginTime);
            traceDir(this.directory, "");
            END_FLAG = true;
            try {
                Thread.sleep(1);
            } catch (InterruptedException exception) {
                endTime =  System.currentTimeMillis();
                System.out.println("终止线程" + Thread.currentThread().getName() + "终止时间" + endTime + "持续时间" + (endTime - beginTime));
            }
        }

        public void traceDir(Directory directory, String s) {
            String path = s + "->" + directory.getDirName();
            directory.getFiles().forEach(file -> {
                if (!END_FLAG) {
                    System.out.println("============" + path + "包含文件：" + file.getFileName() + "." + file.getType() + "============");
                }else{
                    Thread.currentThread().interrupt();
                }

            });
            directory.getChildDir().forEach(dir -> {
                if (!END_FLAG) {
                    traceDir(dir, path);
                }else{
                    Thread.currentThread().interrupt();
                }
            });
        }
    }


}



