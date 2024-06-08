package isep.ipp.pt.api.desofs.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;

@Configuration
@Profile("prod")
public class FirebaseConfig {

    @PostConstruct
    public void configFirebase()  {
        try {


        System.out.println("Firebase Config");
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/desofsPrivateKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()

                .setCredentials(GoogleCredentials.fromStream(serviceAccount))

                .build();
        FirebaseApp.initializeApp(options);
        } catch (IOException e) {

        }
    }

    @Bean
    public Firestore firestore() {
        try {
        return FirestoreClient.getFirestore();
        } catch (Exception e) {
            return null;
        }
    }


}
