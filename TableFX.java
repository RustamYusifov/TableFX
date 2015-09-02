package sample.table;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class TableFX extends Application{
    Stage window;
    TableView<Product>table;
    TextField nameInput,priceInput,quantityInput;

    public static void main(String[]args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage )throws Exception{
        window=primaryStage;
        window.setTitle("Simple Table");

        TableColumn<Product,String>nameColumn=new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Product,Double>priceColumn=new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product,Integer>quantityColumn=new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        nameInput=new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        priceInput=new TextField();
        priceInput.setPromptText("Price");


        quantityInput=new TextField();
        quantityInput.setPromptText("Quantity");

        Button addButton=new Button("Add");
        addButton.setOnAction(e->addButtonCliked());
        Button deleteButton=new Button("Delete");
        deleteButton.setOnAction(e->deleteButtonCliked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput,priceInput,quantityInput,addButton,deleteButton);


        table=new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn,priceColumn,quantityColumn);

        VBox vBox=new VBox();
        vBox.getChildren().addAll(table,hBox);

        Scene scene=new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public void addButtonCliked(){
        Product product=new Product();
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setQuantity(Integer.parseInt(quantityInput.getText()));
        table.getItems().add(product);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }
    public void deleteButtonCliked(){
        ObservableList<Product>productSelected,allProducts;
        allProducts=table.getItems();
        productSelected=table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);

    }

    public ObservableList<Product>getProduct(){
        ObservableList<Product>products= FXCollections.observableArrayList();
        products.add(new Product("Keyboard",9.00,20));
        products.add(new Product("Ball",22.49,198));
        products.add(new Product("PC",1500.56,12));
        products.add(new Product("CD-R",2.56,74));
        products.add(new Product("pencil",3.10,100));
        return products;
    }
}
