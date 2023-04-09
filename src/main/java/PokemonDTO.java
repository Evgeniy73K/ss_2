import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDTO {
    private List<AbilityDTO> abilities;
    private int weight;
    public List<AbilityDTO> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityDTO> abilities) {
        this.abilities = abilities;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static class AbilityDTO {
        private Ability ability;
        private boolean is_hidden;
        private int slot;

        public Ability getAbility() {
            return ability;
        }

        public void setAbility(Ability ability) {
            this.ability = ability;
        }

        public boolean isIs_hidden() {
            return is_hidden;
        }

        public void setIs_hidden(boolean is_hidden) {
            this.is_hidden = is_hidden;
        }

        public int getSlot() {
            return slot;
        }

        public void setSlot(int slot) {
            this.slot = slot;
        }

        public static class Ability {
            private String name;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
