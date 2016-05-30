package com.aldomora.petagram.Presentador;
import android.content.Context;
import com.aldomora.petagram.Fragments.IPetFeed;
import com.aldomora.petagram.Pojo.Mascota;
import com.aldomora.petagram.db.ConstructorMascotas;
import java.util.ArrayList;
/**
 * Created by root on 29/05/16.
 */
public class petFavPresenter implements IPetFavPresenter{
    private IPetFeed iPetFeed;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public petFavPresenter(IPetFeed iPetFeed, Context context){
        this.iPetFeed = iPetFeed;
        this.context = context;
        getMascotas();
    }
    @Override
    public void getMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.getFavData();
        showMascotasRV();
    }
    @Override
    public void showMascotasRV() {
        iPetFeed.startAdapter(iPetFeed.createAdapter(mascotas));
        iPetFeed.generateLinearLayoutVertical();
    }
}
