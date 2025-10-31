package com.example.TallerReceso.RecipeCreator;

import com.example.TallerReceso.Model.recipeCreator.RecipeCreator;
import com.example.TallerReceso.Dto.recipeDTO.RecipeDTO;
import com.example.TallerReceso.Model.document.Chef;
import com.example.TallerReceso.Model.document.Participant;
import com.example.TallerReceso.Model.document.KitchenObserver;
import com.example.TallerReceso.Model.document.Ingredient;
import com.example.TallerReceso.Model.document.Recipe;
import com.example.TallerReceso.Service.ChefService;
import com.example.TallerReceso.Service.ParticipantService;
import com.example.TallerReceso.Service.KitcherObserverService;
import com.example.TallerReceso.util.KitchenRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RecipeCreatorTest {

    @Mock
    private ChefService chefService;
    @Mock
    private ParticipantService participantService;
    @Mock
    private KitcherObserverService kitcherObserverService;

    @InjectMocks
    private RecipeCreator recipeCreator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRecipe_AsChefUser() {
        RecipeDTO dto = new RecipeDTO();
        dto.setRecipeName("Pizza");
        dto.setRecipeDescription("Desc");
        dto.setUserId("chef1");
        dto.setSeason("Summer");
        List<Ingredient> ingredients = List.of(new Ingredient());
        List<String> steps = List.of("step1", "step2");
        Map<String, Object> details = new HashMap<>();
        details.put("ingredients", new ArrayList<>(ingredients));
        details.put("steps", new ArrayList<>(steps));
        dto.setDetails((HashMap<String, Object>) details);

        Chef chef = new Chef();
        chef.setChefName("ChefName");
        chef.setRole(KitchenRole.CHEF);
        // Se llamará dos veces (assignUserName, assignRole)
        when(chefService.findById("chef1")).thenReturn(chef);

        Recipe recipe = recipeCreator.createRecipe(dto);

        assertEquals("Pizza", recipe.getRecipeName());
        assertEquals("Desc", recipe.getRecipeDescription());
        assertEquals("ChefName", recipe.getUserName());
        assertEquals(KitchenRole.CHEF, recipe.getKitchenRole());
        assertEquals("Summer", recipe.getSeason());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(steps, recipe.getSteps());
        verify(chefService, times(2)).findById("chef1");
    }

    @Test
    void createRecipe_AsParticipantUser() {
        RecipeDTO dto = new RecipeDTO();
        dto.setRecipeName("Cake");
        dto.setUserId("part1");
        Map<String, Object> details = new HashMap<>();
        details.put("ingredients", new ArrayList<Ingredient>());
        details.put("steps", new ArrayList<String>());
        dto.setDetails((HashMap<String, Object>) details);

        // chefService lanza excepción, participantService retorna valor.
        when(chefService.findById("part1")).thenThrow(new RuntimeException());
        Participant p = new Participant();
        p.setParticipantName("PartName");
        p.setRole(KitchenRole.PARTICIPANT);
        when(participantService.findById("part1")).thenReturn(p);

        Recipe recipe = recipeCreator.createRecipe(dto);

        assertEquals("PartName", recipe.getUserName());
        assertEquals(KitchenRole.PARTICIPANT, recipe.getKitchenRole());
        verify(chefService, times(2)).findById("part1");
        // Se llama dos veces también (assignUserName, assignRole), pero sólo la segunda retorna resultado
        verify(participantService, times(2)).findById("part1");
    }

    @Test
    void createRecipe_AsObserver() {
        RecipeDTO dto = new RecipeDTO();
        dto.setRecipeName("Soup");
        dto.setUserId("observer1");
        Map<String, Object> details = new HashMap<>();
        details.put("ingredients", new ArrayList<Ingredient>());
        details.put("steps", new ArrayList<String>());
        dto.setDetails((HashMap<String, Object>) details);

        // ambos servicios lanzan excepción, observer retorna valor.
        when(chefService.findById("observer1")).thenThrow(new RuntimeException());
        when(participantService.findById("observer1")).thenThrow(new RuntimeException());
        KitchenObserver obs = new KitchenObserver();
        obs.setObserverName("ObsName");
        obs.setRole(KitchenRole.OBSERVER);
        when(kitcherObserverService.findById("observer1")).thenReturn(obs);

        Recipe recipe = recipeCreator.createRecipe(dto);

        assertEquals("ObsName", recipe.getUserName());
        assertEquals(KitchenRole.OBSERVER, recipe.getKitchenRole());
        verify(chefService, times(2)).findById("observer1");
        verify(participantService, times(2)).findById("observer1");
        verify(kitcherObserverService, times(2)).findById("observer1");
    }

    @Test
    void assignUserName_ThrowsIfNotFound() {
        when(chefService.findById("none")).thenThrow(new RuntimeException());
        when(participantService.findById("none")).thenThrow(new RuntimeException());
        when(kitcherObserverService.findById("none")).thenThrow(new RuntimeException());

        RecipeDTO dto = new RecipeDTO();
        dto.setUserId("none");
        dto.setDetails(new HashMap<>());

        var ex = assertThrows(RuntimeException.class, () -> recipeCreator.createRecipe(dto));
        assertEquals("User not found", ex.getMessage());
        // Solo una llamada a cada uno, ya que nunca se llega a assignRole
        verify(chefService, times(1)).findById("none");
        verify(participantService, times(1)).findById("none");
        verify(kitcherObserverService, times(1)).findById("none");
    }
}


