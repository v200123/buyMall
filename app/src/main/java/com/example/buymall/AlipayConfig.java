package com.example.buymall;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "9021000137603808";//例：2016082600317257

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOJ49yhGdCbakXLm3bIlrKvs2KL3EWL9XmxQxiD3GzZmZH//i9x7RjDYdCShTBJ2iDRhhv5rDMF+vNj/4TT5Tp+D0EHutWKPk33OpkMoVJzYrBZDPjYwOexCIEogIY+N6N2iFNntFUA5dGRv72ZNS6Hga9ZWG1QY+g/iKTkOXQR2u4gHf23iLzAkOjlZ7nZNw7AtiJU/gvqJx9kPzufnFoReCSeu6rGEnBQ3vkurLPL7N143FgCzd+hywRsFHElnEGOw926sX3yGHosirT4dz1ZewxTo7enZI5y11q55Msw5UNvo2WYQeSc9tJV6VLllHi4wz5rlzp/vYLV/D1JeGhAgMBAAECggEAdw0+n6Ovbvy0Dgv+SPW+n+mn7cDJrtElDP2d30ob6SkrI8WGJjpoIkSkMKomM5Wze61qrdy/2E14WFHtcXLGviP9xcO3SBNMlLJZQszkv+h5+njW0+qKj56m9sZPh2Z1zMsP9Re7K+dN+7ddtDDBz5VyA5AygkCD70qm3nsNXJ4PGFPo6Fxs5XHQ9RRwyODQ1ItbjvVo1rQaPXzXHq8uVle8IYGFyPPkm4hJcyDDcjEhTIV5tQq6xtPiH/SS7rk0Ji3rVkATTR3rXJNphn9wyXBdGWCT/ulfaaKxrx4uzINBMuxs+0luoalOHWDCnowcbKNpxIj9KYI0KhaBGcLv+QKBgQDkq1b19V4krQkS04pMfBPBQBdxM4KbiRYlqD+6Gr4xTJuPkf7JyCV0xc2D+f8fr7nsRRJ29/D7ZH9vr9a4nR+WYXHm0oIuMAiIgHoGmHfknOCNnQ3SF/lNZZe1wCJNfAsCKYEKAu3tw8ItZEfAtddpgNv2OwAk7qAt9JnzGAc9IwKBgQCfJRqAgLI4kTj1OlNnQLTYphE63XoC8EyzqnUlCMziu5UJO4aypf0h8ch25e/ZOLgAwg5qAnBy7Um64/NGeGNCo85UNuoLqWhdctwDdjTd4H7+4Ud5t11aR5ND4bv7Q8+1qsdoProXK/NcnO48Z+4wYrM5cmOe5Ar1N+kyBIqcawKBgFJCsQ3aLvvSo6j6II5TNGgYvlYiyaFaN3VkiUhRxonFnVCQnK/KKG9RmRUZIgYsGR492t6hYzeMd6ud/gh7vZlDhM4cfGhRL0CrtzoYL0k2hjFrhkVGCpElMuZXL//f17X3aExJsX4PJhNeN9HDmLKDgJ2bq/mdPlJJ4/fndPMxAoGBAJdnSDBKv8MbPfHnfGN15tdaHng/Lo3PKVmgaHRETA/E4AP5kqsxSYJasRmUpbulv0BRXj8ydOKRpxxbMx2u+Nf0WR47ZCyWDckhtfjBI4iWxJsdnc9JSFHUWGEzR5DDMb+marFdQ9M5p/xK/EeRpMR5kgKsNeR7FYW3nG5ObcRjAoGAbBxoet3GAtDJVmrz2R0ABUlTGu+kuZIKHh+mCnd6wbMH3gEsFwNfjz1OJ4sGRcq0twZpLw0Wh3H9AD8W3H/0DFF2Ct802g9bHXnLF8VwHeRrS97bKGNsGxPPOJvWITaVr1noTy4Lh8S+ykFmXbj7toLIbSFuWZ0W4txsGvQc9SQ=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
    // 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA49HDrk9s/hqnRCpSIEnKaag+C29oF7euuawQyg9zasPD+2YQEE58IRDobIqfgphGTrD6XAh15WNXRdU8fI3X5Q1YAfN5HOq3BjAVqlQ54CE+dATkwucVB+sCvh1dXta/VxnzCQfLiVk6nfUoGvySWunWj0OKyUG1HstfEaX0stFkSTe1Al1Fh6Ll5qMdvtYjO4oW5EyJNx+9OS4BkIBCALAZ1tzGs0Sxwc72A/crwQBCgFCG8QIIBhJKE1hgyd9JO3ZKVfKKq0OnOaM/mUKmvJTIWzdP0bIGsYFtt8AQQefJBGxLEgKcI9tJmm5D6JlCR8huuu6ZqLA1li4eE8QXswIDAQAB";
    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 返回的时候此页面不会返回到用户页面，只会执行你写到控制器里的地址
     */
    public static String notify_url = "你的服务器地址/项目名称/notify_url";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 此页面是同步返回用户页面，也就是用户支付后看到的页面，上面的notify_url是异步返回商家操作，谢谢
     * 要是看不懂就找度娘，或者多读几遍，或者去看支付宝第三方接口API，不看API直接拿去就用，遇坑不怪别人
     */
    public static String return_url = " 你的服务器地址/项目名称/return_url";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "gbk";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 日志地址
    public static String log_path = "D:/logs/";
    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord
     *            要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_"
                    + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
