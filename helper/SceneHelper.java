package helper;

import application.Main;
import javafx.application.Application;

public class SceneHelper {
    public static Main loader = new Main();
    private static String LoginScreen = "/view/TelaLogin.fxml";
    private static String AdminDashboardScreen = "/view/TelaGerente.fxml";
    private static String AdminProductsScreen = "/view/TelaGerenteProdutos.fxml";
    private static String AdminCategoriesScreen = "/view/TelaGerenteCategorias.fxml";
    private static String AdminBrandsScreen = "/view/TelaGerenteMarcas.fxml";
    private static String AdminUsersScreen = "/view/TelaGerenteUsuarios.fxml";
    private static String EmployeeDashboardScreen = "/view/TelaCaixa.fxml";

    public static String getLoginScreen() {
        return LoginScreen;
    }

    public static String getAdminDashboardScreen() {
        return AdminDashboardScreen;
    }

    public static String getAdminProductsScreen() {
        return AdminProductsScreen;
    }

    public static String getAdminCategoriesScreen() {
        return AdminCategoriesScreen;
    }

    public static String getAdminBrandsScreen() {
        return AdminBrandsScreen;
    }

    public static String getAdminUsersScreen() {
        return AdminUsersScreen;
    }

    public static String getEmployeeDashboardScreen() {
        return EmployeeDashboardScreen;
    }
}
