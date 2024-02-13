package in.gov.wildlife.mis.credential.authentication;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.gimpy.RippleGimpyRenderer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import in.gov.wildlife.mis.common.Constants;
import in.gov.wildlife.mis.common.MessageByLocale;
import in.gov.wildlife.mis.exception.InvalidCaptchaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaService {
    private static final Integer EXPIRE_MINS = 30;
    private LoadingCache<String, String> captchaCache;

    @Autowired
    private MessageByLocale messageByLocale;

    public CaptchaService(){

        captchaCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    public String load(String key,String value) throws Exception {
                        // TODO Auto-generated method stub
                        return generateCaptcha(key,value);
                    }

                    @Override
                    public String load(String key)  {
                        // TODO Auto-generated method stub
                        return null;
                    }
                });
    }

    public Captcha createCaptcha(Integer width, Integer height) {

        return new Captcha.Builder(width, height)
                .addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(), new DefaultWordRenderer())
//                .addNoise(new StraightLineNoiseProducer())
                .gimp(new RippleGimpyRenderer())
                .build();
    }

    //Converting to binary String
    public String encodeCaptcha(Captcha captcha)  {
        String image = null;
        try {
            ByteArrayOutputStream bos= new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(),"jpg", bos);
            byte[] byteArray= Base64.getEncoder().encode(bos.toByteArray());
           image = new String(byteArray);
//            return byteArray;
        } catch (IOException e) {
            throw new InvalidCaptchaException(messageByLocale.getMessage(Constants.ERROR_MESSAGE.INVALID_CAPTCHA));
        }
        return image;
    }

    public String generateCaptcha(String key,String value) {
        //	Captcha captcha = createCaptcha(240, 70);
        captchaCache.put(key, value);
        return value;

    }
    public Boolean validateCaptcha(String key, String captcha) {
        return (getCaptcha(key).equals(captcha));

    }
    public String getCaptcha(String key){
        try{
            return  captchaCache.get(key);
        }catch ( ExecutionException e){
            return null;
        }
    }

    public void clearCaptcha(String key){
        captchaCache.invalidate(key);
    }

    public String getValueOfCaptcha(String ip) throws ExecutionException {
       return captchaCache.get(ip);
    }

}

