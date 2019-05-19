package mongodata.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3ClientService
{
    void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess);

    S3Object downloadFileFromS3Bucket(String fileName, String bucketName);

    void deleteFileFromS3Bucket(String fileName);
}
