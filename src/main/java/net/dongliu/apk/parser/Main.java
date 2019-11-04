package net.dongliu.apk.parser;

import net.dongliu.apk.parser.bean.ApkMeta;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Locale;
import java.util.Map;

/**
 * Main method for parser apk
 *
 * @author Liu Dong {@literal <dongliu@live.cn>}
 */
public class Main {
    public static void main(String[] args) throws IOException, CertificateException {
        String action = args[0];
        String apkPath = args[1];

        if(!new File(apkPath).exists()){
            System.err.println("！！！！apk文件不存在");
            System.exit(1);
        }

        try (ApkFile apkFile = new ApkFile(apkPath)) {
            apkFile.setPreferredLocale(Locale.getDefault());
            switch (action) {
                case "meta":
                    System.out.println(apkFile.getApkMeta());
                    break;
                case "versionName":
                    System.out.println(apkFile.getApkMeta().getVersionName());
                    break;
                case "versionCode":
                    System.out.println(apkFile.getApkMeta().getVersionCode());
                    break;
                case "manifest":
                    System.out.println(apkFile.getManifestXml());
                    break;
                case "issue":
                    String issue = getMetaIssue(apkFile.getApkMeta(),"issue");
                    System.out.println(issue);
                    break;
                case "build_type":
                    String buildType = getMetaIssue(apkFile.getApkMeta(),"build_type");
                    System.out.println(buildType);
                    break;
                case "signer":
                    System.out.println(apkFile.getApkSingers());
                    break;
                default:
                    System.out.println("UnSupport arguments:"+action);
            }
        }
    }

    public static String getMetaIssue(ApkMeta apkMeta,String key){

        Map<String,String> metaData = apkMeta.getMetaData();
        if(metaData != null && metaData.containsKey(key)){
            return metaData.get(key);
        }
        return "";

    }
}
