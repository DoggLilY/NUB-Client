package com.lilyfena.NUBClient.Module;

import com.lilyfena.NUBClient.Module.COMBAT.AutoTratilo;
import com.lilyfena.NUBClient.Module.PLAYER.Mkill;
import com.lilyfena.NUBClient.Module.PLAYER.Sprint;
import com.lilyfena.NUBClient.Module.PLAYER.Uuuiiiii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleManager {

    public static final ModuleManager INSTANCE = new ModuleManager();
    private List<Mod> modules = new ArrayList<>();
    public ModuleManager() {
        addModules();
    }

    public List<Mod> getModules() {
        return modules;
    }

    public List<Mod> getEnabledModules() {
        List<Mod> enabled = new ArrayList<>();
        for (Mod module : modules) {
            if (module.isEnabled()) {
                enabled.add(module);
            }
        }

        return enabled;
    }

    public List<Mod> getModulesCategory(Mod.Category category) {
        List<Mod> categoryModules = new ArrayList<>();

        for (Mod mod : modules) {
            if (mod.getCategory() == category) {
                categoryModules.add(mod);
            }
        }
        return categoryModules;
    }

    private void addModules() {
        modules.addAll(Arrays.asList(
                //COMBAT
                new AutoTratilo(),
                //PLAYER
                new Mkill(),
                new Sprint(),
                new Uuuiiiii()
        ));
    }


}
