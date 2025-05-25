package Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import Domain.FoodDomain;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDb;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDb = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready = false;

        int n = 0;
        for (int i=0; i<listFood.size(); ++i){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }

        tinyDb.putListObject("CardList", listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart(){
        return tinyDb.getListObject("CartList");
    }
}
