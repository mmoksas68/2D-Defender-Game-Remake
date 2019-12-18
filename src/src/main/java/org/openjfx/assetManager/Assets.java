package org.openjfx.assetManager;

public class Assets {
    private static Assets assets = null;
    private PreBossAssets preBossAssets;
    private MenuAssets menuAssets;


    private Assets()
    {

        preBossAssets = new PreBossAssets();
        menuAssets = new MenuAssets();
    }

    public static Assets getInstance()
    {
        if (assets == null)
            assets = new Assets();

        return assets;
    }

    public PreBossAssets getPreBossAssets() {
        return preBossAssets;
    }

    public void setPreBossAssets(PreBossAssets preBossAssets) {
        this.preBossAssets = preBossAssets;
    }

    public MenuAssets getMenuAssets(){return menuAssets;}


}
