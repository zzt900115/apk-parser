package net.dongliu.apk.parser;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Locale;

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
                case "signer":
                    System.out.println(apkFile.getApkSingers());
                    break;
                default:
            }

        }
    }
}
