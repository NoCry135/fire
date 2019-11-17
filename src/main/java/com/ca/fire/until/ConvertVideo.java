package com.ca.fire.until;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ConvertVideo {
    /**
     * @param args
     */
    //private final static String PATH="c:\\ffmpeg\\input\\test.avi";
    private static String inputPath = "";
    private static String outputPath = "";
    private static String ffmpegPath = "";

    public static void main(String[] args) {
        getPath("D:\\apache-tomcat-7.0.55\\webapps\\ResourcesShare\\WEB-INF\\uploadFile\\0011.51CTO学院-struts2hibernatespring整合第十一章视频.mp4", "D:\\apache-tomcat-7.0.55\\webapps\\ResourcesShare\\tools\\ffmpeg", "D:\\apache-tomcat-7.0.55\\webapps\\ResourcesShare\\tools\\ffmpeg\\output");
        if (!checkfile(inputPath)) {
            System.out.println(inputPath + " is not file");
            return;
        }
        if (process()) {
            System.out.println("ok");
        }

    }

    private static void getPath(String input, String ffmpeg, String output) {
        inputPath = input;
        outputPath = output;
        ffmpegPath = ffmpeg;
    }

    public static void getRun(String inputPath, String ffmegPath, String outputPath) {
        System.out.println(inputPath + "  " + ffmegPath + " " + outputPath);
        getPath(inputPath, ffmegPath, outputPath);
        if (!checkfile(inputPath)) {
            System.out.println(inputPath + " is not file");
            return;
        }
        if (process()) {
            System.out.println("ok");
        }
    }

    private static boolean process() {
        int type = checkContentType();
        boolean status = false;
        if (type == 0) {
            System.out.println("直接将文件转换为flv文件");
            status = processFLV(inputPath); //直接将文件转为flv文件

        } else if (type == 1) {
            System.out.println("先转成avi");
            String avifilepath = processAVI(type);
            if (avifilepath == null) {
                return false; //avi文件没有得到
            }
            status = processFLV(avifilepath);
        }
        return status;

    }

    private static int checkContentType() {
        String type = inputPath.substring(inputPath.lastIndexOf(".") + 1, inputPath.length()).toLowerCase();
        //ffmpeg能解难析的格式 ：（asx,asf,mpg,wmv,3gp,mp4,mov,avi,flv等）
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mpg")) {
            return 0;
        } else if (type.equals("asx")) {
            return 0;
        } else if (type.equals("asf")) {
            return 0;
        } else if (type.equals("wmv")) {
            return 0;
        } else if (type.equals("3gp")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("avi")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        }
        //对ffmpeg无法解析的文件格式（wmv9,rm,rmvb等）
        //可以先用别的工具（mencoder）转换为avi（ffmpeg能解析的）格式
        else if (type.equals("wmv9")) {
            return 1;
        } else if (type.equals("rm")) {
            return 1;
        } else if (type.equals("rmvb")) {
            return 1;
        }

        return 9;
    }

    public static boolean checkfile(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            return false;
        }
        return true;
    }

    //对ffmpeg无法解析匆文件格式（wmv9，rm,rmvb等，可以先用别的（moncoder）转换为avi）
    private static String processAVI(int type) {
        List<String> commend = new ArrayList<String>();
        commend.add(ffmpegPath + "\\mencoder");
        commend.add(inputPath);
        commend.add("-oac");
        commend.add("lavc");
        commend.add("-lavcopts");
        commend.add("acodec=mp3:abitrate=64");
        commend.add("-ovc");
        commend.add("xvid");
        commend.add("-xvidencopts");
        commend.add("bitrate=600");
        commend.add("-of");
        commend.add("avi");
        commend.add("-o");
        commend.add(outputPath + "\\a.avi");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.start();
            return outputPath + "a.avi";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //ffmpeg能解析格式：（asx,asf,mpg,wmv,3gp.mp4.mov,avi.flv等）
    private static boolean processFLV(String oldfilepath) {
        if (!checkfile(inputPath)) {
            System.out.println(oldfilepath + "is not file");
            return false;
        }
        //文件命名
        //Calendar c=Calendar.getInstance();
        //String savename=String.valueOf(c.getTimeInMillis())+Math.round(Math.random()*100000);
        List<String> commend = new ArrayList<String>();
        commend.add(ffmpegPath + "\\ffmpeg");
        commend.add("-i");
        commend.add(oldfilepath);
        commend.add("-ab");
        commend.add("56");
        commend.add("-ar");
        commend.add("22050");
        commend.add("-qscale");
        commend.add("8");
        commend.add("-r");
        commend.add("15");
        commend.add("-s");
        commend.add("600*500");
//        commend.add(FileUtil.changeType(inputPath, "flv"));

        try {
            //			Runtime runtime=Runtime.getRuntime();
            //			Process proce=null;
            //			String cmd="";
            //			String cut="    c:\\ffmpeg\\ffmpeg.exe  -i  "
            //			+oldfilepath
            //			+"   -y   -f  image2   -ss   8   -t   0.001   -s   600*500   c:\\ffmpeg\\output\\"
            //			+" a.jpg";
            //			String cutCmd=cmd+cut;
            //			proce=runtime.exec(cutCmd);


            Process videoProcess = new ProcessBuilder(commend).redirectErrorStream(true).start();
            new ClearOutput(videoProcess.getErrorStream()).start();
            new ClearOutput(videoProcess.getInputStream()).start();

            videoProcess.waitFor();

//            FileUtil.delFile(inputPath);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static class ClearOutput extends Thread {

        InputStream in = null;

        public ClearOutput(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            int len = -1;
            try {
                while ((len = in.read()) != -1) {
                    System.out.println(len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //获取文件后缀名
    public static String getType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    //改文件后缀名
    public static String changeType(String fileName, String type) {

        return fileName.substring(0, fileName.lastIndexOf(".") + 1) + type;
    }

    //删除文件
    public static void delFile(String path) {
        File file = new File(path);
        if (file.isFile()) {
            file.delete();
        }
    }

}
