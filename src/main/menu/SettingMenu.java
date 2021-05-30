package main.menu;

public class SettingMenu extends Menu{
    public SettingMenu(Menu parentMenu) {
        super(parentMenu, "SETTINGS");
    }
    public void show() {
        System.out.println("I'm Settings");
    }

    @Override
    public void execute() {
        System.out.println("execute in Settings");
        this.parentMenu.show();
        this.parentMenu.execute();
    }
}
