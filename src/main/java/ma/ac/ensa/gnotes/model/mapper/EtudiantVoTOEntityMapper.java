package ma.ac.ensa.gnotes.model.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EtudiantVoTOEntityMapper {
    private ModelMapper modelMapper;
    public EtudiantVoTOEntityMapper(){
        this.modelMapper = new ModelMapper();
        Converter<String, Date> stringToDate = new AbstractConverter<String, Date>() {
            protected Date convert(String source) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    return source == null ? null : df.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        this.modelMapper.addConverter(stringToDate);
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
