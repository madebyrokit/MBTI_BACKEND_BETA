package backend.mbti.service;

import backend.mbti.domain.Member;
import backend.mbti.configuration.exception.AppException;
import backend.mbti.configuration.exception.ErrorCode;
import backend.mbti.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final MemberRepository memberRepository;

    @Value("${file.upload-dir}")
    private String filePath;
    public String getFullPath(String filename) {
        return filePath + filename;
    }

    @Override
    public void upload(String username, MultipartFile multipartFile) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        String fileName = storeFile(multipartFile);
        member.getProfileImage().setOriginalFileName(multipartFile.getName());
        member.getProfileImage().setStoreFileName(fileName);
        memberRepository.save(member);
    }

    @Override
    public String storeFile(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String storeFileName = UUID.randomUUID() + fileExtension;

        try {
            multipartFile.transferTo(new File(getFullPath(storeFileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return storeFileName;
    }
}
