/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package product;

import java.util.ArrayList;

/**
 *
 * @author Ben
 */
public class ProductList {
    public String dbError = "";
    private ArrayList<Product> productList = new ArrayList();

    public ProductList() {
    }

    public void addOption(Product option) {
        this.productList.add(option);
    }
}
