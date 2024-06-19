package edu.miu.emailsender.mapper;

import edu.miu.emailsender.domain.EmailObject;
import edu.miu.emailsender.dto.EmailObjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

/**
 * @author kush
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DtoMapper {
    DtoMapper dtoMapper =
            Mappers.getMapper(DtoMapper.class);


    @Mapping(source = "to", target = "to", qualifiedByName = "listToString")
    @Mapping(source = "cc", target = "cc", qualifiedByName = "listToString")
    @Mapping(source = "bcc", target = "bcc", qualifiedByName = "listToString")
    EmailObject emailObjectDtoToEmailObject(EmailObjectDto emailObjectDto);

    @Mapping(source = "to", target = "to", qualifiedByName = "stringToList")
    @Mapping(source = "cc", target = "cc", qualifiedByName = "stringToList")
    @Mapping(source = "bcc", target = "bcc", qualifiedByName = "stringToList")
    EmailObjectDto emailObjectToEmailObjectDto(EmailObject emailObject);

    @Named("stringToList")
    public static List<String> stringToList(String value) {
        return value != null ? Arrays.asList(value.split(",")) : null;
    }

    @Named("listToString")
    public static String listToString(List<String> value) {
        return value != null ? String.join(",", value) : null;
    }



}