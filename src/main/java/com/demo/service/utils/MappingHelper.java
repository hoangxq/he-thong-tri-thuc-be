package com.demo.service.utils;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MappingHelper {
    private ModelMapper modelMapper;

    public MappingHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T, H> List<T> mapList(List<H> inputData, Class<T> clazz) {
        return CollectionUtils.isEmpty(inputData) ?
                Collections.emptyList() :
                inputData.stream()
                        .map(i -> modelMapper.map(i, clazz))
                        .collect(Collectors.toList());
    }

    public <T, H> T map(H inputData, Class<T> clazz) {
        return modelMapper.map(inputData, clazz);
    }

    public <T, H> void mapIfSourceNotNullAndStringNotBlank(T source, H destination){
        modelMapper.getConfiguration().setPropertyCondition(isStringNotBlank());
        modelMapper.map(source, destination);
    }

    private Condition<Object, Object>isStringNotBlank(){
        return new AbstractCondition<Object, Object>() {
            @Override
            public boolean applies(MappingContext<Object, Object> mappingContext) {
                if(mappingContext.getSource() instanceof String)
                    return StringUtils.isNoneBlank(String.valueOf(mappingContext.getSource()));
                return Objects.nonNull(mappingContext.getSource());
            }
        };
    }
}