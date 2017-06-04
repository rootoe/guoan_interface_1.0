package com.guoan.utils.vedio;

import com.guoan.utils.AliyunOSSUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.*;


/**
 * @Description : 语音转换
 * @创建人：赵彤
 * @创建时间：2015年5月4日 下午6:53:05
 */
@Component
public class VedioUtils implements ServletContextAware {

    public static BufferedReader amr2wav(String s_comand, String foler,String savePath) {
        return amr2wav(s_comand, null, foler, savePath);
    }

    public static BufferedReader amr2wav(String s_comand, File path, String foler,String savePath) {
        BufferedReader br = null;

        String system = System.getProperty("os.name");
        if (system != null) {
            if (system.toLowerCase().indexOf("linux") != -1) {
                Process p = null;
                try {
                    Runtime sys = Runtime.getRuntime();

                    if (path == null)
                        p = sys.exec(s_comand);
                    else {
                        p = sys.exec(s_comand, null, path);
                    }
                    br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (system.toLowerCase().indexOf("windows") != -1) {
                Runtime runtime = Runtime.getRuntime();
                Process process = null;
                InputStream is = null;
                InputStreamReader isr = null;
                try {
                    if (path == null) {

//                        process = runtime.exec("cmd /c \"" + s_comand + "\"");
//                        process = runtime.exec("cmd -c \"" + s_comand + "\"");
                        process = runtime.exec(new String[]{"cmd ", "/c", s_comand});
//                        process = runtime.exec("cmd " + s_comand );
                    } else {
                        process = runtime.exec("cmd /c \"" + s_comand + "\"", null, path);
                    }
                    is = process.getInputStream();
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);

                    String line = null;
                    System.out.println("13 -------------------------------------  "+br.readLine());
                    String inline;
                    while ((inline = br.readLine()) != null) {
                        System.out.println(inline);
                    }

                    int exitVal = 0;
                    try {
                        exitVal = process.waitFor();
                        if(exitVal == 0){
                            System.out.println("走了...");
                            System.out.println("Command Succeed");
                            AliyunOSSUtils.upload_pic( foler, savePath);

                        }else{
                            System.out.println("Command Failed");

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    br.close();

                } catch (IOException e) {
                    System.out.println(e);
//                    runtime.exit(1);
                }
                return br;
            }
        }
        return br;
    }

    /**
     * 将源MP3向下转码成低品质的文件
     * @参数： @param srcPath 源地址
     * @参数： @param bitrate 比特率
     * @参数： @param desfile 目标文件
     * @return void
     * @throws
     */
    public static void mp3Transcoding(String srcPath,String bitrate,String foler, String saveFile) {
        //Java调用CMD命令时,不能有空格
        String srcpath = srcPath.replace(" ", "\" \"");
        String desfile = saveFile.replace(" ", "\" \"");
        Runtime rt = Runtime.getRuntime();
        // windows
//        String command = "cmd /c ffmpeg -loglevel quiet -i "+srcpath+" -ab "+bitrate+"k -acodec libmp3lame "+desfile;
        // linux
        String command = "ffmpeg -loglevel quiet -i "+srcpath+" -ab "+bitrate+"k -acodec libmp3lame "+desfile;
        System.out.println(command);
        Process p = null;
        try{
            //在Linux下调用是其他写法
//            p = rt.exec(command ,null,new File("C:\\ffmpeg-git-670229e-win32-static\\bin"));
            p = rt.exec(command ,null);
            p.waitFor();
            System.out.println("线程返回,转码后的文件大小为："+saveFile.length()+",现在可以做其他操作了。");
            AliyunOSSUtils.upload_pic( foler, saveFile);
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            }
            catch(Exception ee){}
        }
    }


    public static void main(String[] args) {

//        mp3Transcoding("https://a1.easemob.com/guoanshequ/guoanshequ/chatfiles/ee29c750-09ee-11e5-a506-db41087a3746","64","c:\\chenmiao.mp3");

    }

    public void readLinesFromFile(String filename) {

        BufferedReader br = null;

        try {
            //构造BufferedReader对象
            br = new BufferedReader(new FileReader(filename));

            String line = null;
            while ((line = br.readLine()) != null) {

                //将文本打印到控制台
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //关闭BufferedReader
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext = servletContext;

    }
}