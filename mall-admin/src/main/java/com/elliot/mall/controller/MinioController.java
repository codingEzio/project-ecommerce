package com.elliot.mall.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.elliot.mall.common.api.CommonResult;
import com.elliot.mall.dto.BucketPolicyConfigDto;
import com.elliot.mall.dto.MinioUploadDto;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteArgs;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketPolicyArgs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Api(tags = "MinioController")
@Tag(name = "MinioController", description = "MinIO对象存储管理")
@RequestMapping("/minio")
public class MinioController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

	@Value("${minio.endpoint}") // Injecting value from properties file
	private String ENDPOINT;

	@Value("${minio.bucketName}") // Injecting value from properties file
	private String BUCKET_NAME;

	@Value("${minio.accessKey}") // Injecting value from properties file
	private String ACCESS_KEY;

	@Value("${minio.secretKey}") // Injecting value from properties file
	private String SECRET_KEY;

	/**
	 * Uploads a file to the MinIO object storage
	 *
	 * @param file The file to be uploaded
	 *
	 * @return A CommonResult object containing the result of the upload
	 */
	@ApiOperation("文件上传")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult upload(@RequestPart("file") MultipartFile file) {
		try {
			// Creating a MinIO client
			MinioClient minioClient = MinioClient.builder()
					.endpoint(ENDPOINT)
					.credentials(ACCESS_KEY, SECRET_KEY)
					.build();

			boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
			if (isExist) {
				LOGGER.info("存储桶已经存在！");
			} else {
				// Creating a bucket and setting read-only permissions
				minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
				BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(BUCKET_NAME);
				SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
						.bucket(BUCKET_NAME)
						.config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
						.build();
				minioClient.setBucketPolicy(setBucketPolicyArgs);
			}

			String filename = file.getOriginalFilename();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

			// Setting the object name
			String objectName = simpleDateFormat.format(new Date()) + "/" + filename;
			// Uploading the file to the bucket
			PutObjectArgs putObjectArgs = PutObjectArgs.builder()
					.bucket(BUCKET_NAME)
					.object(objectName)
					.contentType(file.getContentType())
					.stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();

			minioClient.putObject(putObjectArgs);
			LOGGER.info("文件上传成功!");

			MinioUploadDto minioUploadDto = new MinioUploadDto();
			minioUploadDto.setName(filename);
			minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);

			return CommonResult.success(minioUploadDto);
		} catch (Exception e) {
			e.printStackTrace();

			LOGGER.info("上传发生错误: {}！", e.getMessage());
		}
		return CommonResult.failed();
	}

	/**
	 * Deletes a file from the MinIO object storage
	 *
	 * @param objectName The name of the file to be deleted
	 *
	 * @return A CommonResult object containing the result of the delete operation
	 */
	@ApiOperation("文件删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delete(@RequestParam("objectName") String objectName) {
		try {
			// Creating a MinIO client
			MinioClient minioClient = MinioClient.builder()
					.endpoint(ENDPOINT)
					.credentials(ACCESS_KEY, SECRET_KEY)
					.build();

			minioClient.removeObject(RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build());

			return CommonResult.success(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CommonResult.failed();
	}

	/**
	 * Creates a bucket policy configuration object
	 *
	 * @param bucketName The name of the bucket to create the policy for
	 *
	 * @return A BucketPolicyConfigDto object containing the policy configuration
	 */
	private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
		BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
				.Effect("Allow")
				.Principal("*")
				.Action("s3:GetObject")
				.Resource("arn:aws:s3:::" + bucketName + "/*.**").build();

		return BucketPolicyConfigDto.builder()
				.Version("2012-10-17")
				.Statement(CollUtil.toList(statement))
				.build();
	}
}
