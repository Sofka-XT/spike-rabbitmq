package grupoexito.priorizacion_mensajes.domain.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.Normalizer;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    private static final Map<String, String> characters = Map.of("\uFFFD", "ñ",
            "\u200B", "",
            "\u0027", "",
            "&nbsp;", "",
            //"\\u0022", "'",
            "“", "\\u0022",
            "\u003c", "<",
            "\u003d", "="
            //"\\'", "'"
    );

    public static boolean isEmpty(String str){
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(String... strings){
        boolean empty = false;
        for(String str : strings){
            empty = empty || isEmpty(str);
        }
        return empty;
    }


    public static String removeAccents(String value) {
        return Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public static String removeSpecialCharacters(String value) {
        for (Map.Entry<String, String> entry : characters.entrySet()) {
            value = value.replaceAll(entry.getKey(), entry.getValue());
        }
        return Normalizer.normalize(value, Normalizer.Form.NFD);
    }
}
