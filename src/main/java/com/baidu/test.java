package com.baidu;

import java.net.URL;

/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.URISyntaxException;
/*      */ import java.net.URL;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.zip.GZIPInputStream;
/*      */ import javax.script.Invocable;
/*      */ import javax.script.ScriptEngine;
/*      */ import javax.script.ScriptEngineManager;
/*      */ import org.apache.commons.httpclient.HttpClient;
/*      */ import org.apache.commons.httpclient.HttpMethod;
/*      */ import org.apache.commons.httpclient.methods.PostMethod;
/*      */ import org.apache.commons.httpclient.methods.RequestEntity;
/*      */ import org.apache.commons.httpclient.methods.StringRequestEntity;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.apache.http.HttpEntity;
/*      */ import org.apache.http.HttpResponse;
/*      */ import org.apache.http.client.ClientProtocolException;
/*      */ import org.apache.http.client.ResponseHandler;
/*      */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*      */ import org.apache.http.client.methods.CloseableHttpResponse;
/*      */ import org.apache.http.client.methods.HttpPost;
/*      */ import org.apache.http.client.methods.HttpUriRequest;
/*      */ import org.apache.http.entity.ContentType;
/*      */ import org.apache.http.entity.StringEntity;
/*      */ import org.apache.http.entity.mime.HttpMultipartMode;
/*      */ import org.apache.http.entity.mime.MultipartEntityBuilder;
/*      */ import org.apache.http.impl.client.CloseableHttpClient;
/*      */ import org.apache.http.impl.client.HttpClients;
/*      */ import org.apache.http.message.BasicNameValuePair;
/*      */ import org.apache.http.util.EntityUtils;
/*      */ import org.springframework.web.multipart.MultipartFile;

public class test {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String cookieStr="__yjs_duid=1_f5fb48ffa973be3ae80a6c4229168e511629883841678; CLIENT_SYS_UN_ID=3rvgCmEmDcJn8KbZP81LAg==; s_v=cdh%3D%3E27a30245%7C%7C%7Cvid%3D%3E1629883844435772025%7C%7C%7Cfsts%3D%3E1629883844%7C%7C%7Cdsfs%3D%3E166%7C%7C%7Cnps%3D%3E33; s_rfd=cdh%3D%3E27a30245%7C%7C%7Ctrd%3D%3Ewww.baidu.com%7C%7C%7Cftrd%3D%3Ebaidu.com; s_s=cdh%3D%3E27a30245%7C%7C%7Clast_req%3D%3E1644249028%7C%7C%7Csid%3D%3E1644249028752452263%7C%7C%7Cdsps%3D%3E10; max_u_token=678fb032c949c76b4dd325e5c57fc432";
        String url0Info = "https://max.book118.com/user_center_v1/upload/Upload/ordinary.html";
        String resCS00 = getHttpResStringWithCookie(url0Info, cookieStr);
        System.out.println(resCS00);
    }
    public static String getHttpResStringWithCookie(String urlStr, String cookie) throws URISyntaxException, ClientProtocolException, IOException {
        /*  915 */     String resHtml = "";
        /*  916 */     StringBuffer stringBuffer = new StringBuffer();
        /*      */
        /*      */     try {
            /*  919 */       URL url = new URL(urlStr);
            /*  920 */       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            /*      */
            /*      */
            /*  923 */       conn.setConnectTimeout(3000);
            /*  924 */       if (cookie != null) {
                /*  925 */         conn.setRequestProperty("Cookie", cookie);
                /*      */       }
            /*      */
            /*  928 */       conn.setRequestProperty("User-Agent",
                    /*  929 */           "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
            /*      */
            /*  931 */       GZIPInputStream gZIPInputStream = null;
            /*  932 */       String encoding = conn.getContentEncoding();
            /*  933 */       if (encoding != null && "gzip".equals(encoding)) {
                /*  934 */         gZIPInputStream = new GZIPInputStream(conn.getInputStream());
                /*  935 */         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream));
                /*  936 */         String line = null;
                /*  937 */         while ((line = bufferedReader.readLine()) != null) {
                    /*      */
                    /*  939 */           line = new String(line.getBytes("UTF-8"));
                    /*  940 */           stringBuffer.append(line);
                    /*      */         }
                /*  942 */         bufferedReader.close();
                /*      */       } else {
                /*  944 */         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                /*  945 */         String line = null;
                /*  946 */         while ((line = bufferedReader.readLine()) != null) {
                    /*      */
                    /*  948 */           line = new String(line.getBytes("UTF-8"));
                    /*  949 */           stringBuffer.append(line);
                    /*      */         }
                /*  951 */         bufferedReader.close();
                /*      */       }
            /*      */
            /*      */
            /*  955 */       conn.disconnect();
            /*  956 */       if (gZIPInputStream != null) {
                /*  957 */         gZIPInputStream.close();
                /*      */       }
            /*  959 */     } catch (Exception e) {
            /*  960 */       e.printStackTrace();
            /*      */     }
        /*      */
        /*      */
        /*      */
        /*  965 */     return stringBuffer.toString();
        /*      */   }
}
