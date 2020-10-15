package br.com.zup.nossobancodigital.api.model.input;

import br.com.zup.nossobancodigital.core.validation.FileContentType;
import br.com.zup.nossobancodigital.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoInput {

    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    @FileSize(max = "500KB")
    @NotNull
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;
}
