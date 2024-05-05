# Makefile pour les programmes Joueur et Concepteur

# Compilateur Java
JAVAC = javac

# Options de compilation
JAVAC_FLAGS = -g

# Répertoire parent
PARENT_DIR = SAE21_2023

# Répertoire source pour le programme Joueur
JOUEUR_SRC_DIR = $(PARENT_DIR)/joueur

# Répertoire source pour le programme Concepteur
CONCEPTEUR_SRC_DIR = $(PARENT_DIR)/concepteur

# Répertoire des classes
BIN_DIR = $(PARENT_DIR)/bin

# Liste des fichiers sources pour le programme Joueur
JOUEUR_SOURCES = $(wildcard $(JOUEUR_SRC_DIR)/*.java)

# Liste des fichiers sources pour le programme Concepteur
CONCEPTEUR_SOURCES = $(wildcard $(CONCEPTEUR_SRC_DIR)/*.java)

# Liste des classes pour le programme Joueur
JOUEUR_CLASSES = $(patsubst $(JOUEUR_SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(JOUEUR_SOURCES))

# Liste des classes pour le programme Concepteur
CONCEPTEUR_CLASSES = $(patsubst $(CONCEPTEUR_SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(CONCEPTEUR_SOURCES))

# Cible par défaut
all: joueur concepteur

# Cible de compilation pour le programme Joueur
joueur: $(JOUEUR_CLASSES)

# Cible de compilation pour le programme Concepteur
concepteur: $(CONCEPTEUR_CLASSES)

# Règle de compilation des fichiers source Java pour le programme Joueur
$(BIN_DIR)/%.class: $(JOUEUR_SRC_DIR)/%.java
	$(JAVAC) $(JAVAC_FLAGS) -d $(BIN_DIR) $<

# Règle de compilation des fichiers source Java pour le programme Concepteur
$(BIN_DIR)/%.class: $(CONCEPTEUR_SRC_DIR)/%.java
	$(JAVAC) $(JAVAC_FLAGS) -d $(BIN_DIR) $<

# Nettoyer les fichiers compilés
clean:
	rm -f $(BIN_DIR)/*.class
