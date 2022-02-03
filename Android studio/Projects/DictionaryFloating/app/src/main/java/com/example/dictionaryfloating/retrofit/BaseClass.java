package com.example.dictionaryfloating.retrofit;

import java.util.List;

public class BaseClass {

    private final String word;
    private final String phonetic;
    private List<Meanings> meanings;

    public BaseClass(String word, String phonetic, List<Meanings> meanings) {
        this.word = word;
        this.phonetic = phonetic;
        this.meanings = meanings;
    }

    public String getWord() {
        return word;
    }

    public String getPhonetic() {
        return phonetic;
    }


    public List<Meanings> getMeanings() {
        return meanings;
    }

    @Override
    public String toString() {
        return  "word='" + word + '\'' +
                ", phonetic='" + phonetic + '\'' +
                ", meanings=" + meanings +
                '}';
    }

    public class Meanings {
        private List<Definitions> definitions;

        public Meanings(Definitions definitions) {
            this.definitions = (List<Definitions>) definitions;
        }

        public List<Definitions> getDefinitions() {
            return definitions;
        }

        public void setDefinitions(List<Definitions> definitions) {
            this.definitions = definitions;
        }

        @Override
        public String toString() {
            return " " + definitions;
        }

        public class Definitions {
            String definition;

            public void setDefinition(String definition) {
                this.definition = definition;
            }

            public Definitions(String definition) {
                this.definition = definition;
            }

            public String getDefinition() {
                return definition;
            }

            @Override
            public String toString() {
                return
                        "definition='" + definition ;
            }
        }
    }
}
