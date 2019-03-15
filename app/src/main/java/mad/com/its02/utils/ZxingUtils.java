package mad.com.its02.utils;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;

public class ZxingUtils {
    public static Bitmap createBitmap(String str, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        try {
            BitMatrix encode = qrCodeWriter.encode(str,BarcodeFormat.QR_CODE, width, height,hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height;i++) {
                for (int j = 0; j < width;j++) {
                    if (encode.get(i,j)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }

//            return Bitmap.createBitmap(pixels,0,width,width,height,height,Bitmap.Config.RGB_565);
            return Bitmap.createBitmap(pixels,width,height, Bitmap.Config.RGB_565);



        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;
    }

}
