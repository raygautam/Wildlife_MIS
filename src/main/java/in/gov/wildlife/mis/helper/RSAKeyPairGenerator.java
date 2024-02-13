package in.gov.wildlife.mis.helper;

import org.springframework.stereotype.Component;

import java.security.*;

@Component
public class RSAKeyPairGenerator {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }




    public void createKeys() throws NoSuchAlgorithmException{
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

}
