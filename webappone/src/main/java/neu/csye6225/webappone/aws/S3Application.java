package neu.csye6225.webappone.aws;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;

@Configuration
public class S3Application {
    @Value("us-east-1") //("${AWS_DEFAULT_REGION}")
    String region;

    @Bean
    public AmazonS3 s3client() {
        //.withCredentials(new InstanceProfileCredentialsProvider(false))
        AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .build();
        return amazonS3Client;
    }
}
