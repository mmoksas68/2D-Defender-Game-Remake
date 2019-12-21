package org.openjfx.assetManager;

public class Assets {
    private static Assets assets = null;
    private PreBossAssets preBossAssets;
    private BossAssets bossAssets;
    private MenuAssets menuAssets;


    private Assets()
    {
        preBossAssets = new PreBossAssets();
        bossAssets = new BossAssets();
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

    public BossAssets getBossAssets(){ return bossAssets; }

    public MenuAssets getMenuAssets(){return menuAssets;}


}
