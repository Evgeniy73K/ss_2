import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PokemonDTO {

    private List<AbilityDTO> abilities;
    private int weight;

    @Getter
    @Setter
    public static class AbilityDTO {
        private Ability ability;
        private boolean is_hidden;
        private int slot;


        public boolean isIs_hidden() {
            return is_hidden;
        }

        @Getter
        @Setter
        public static class Ability {
            private String name;
            private String url;


        }
    }
}
