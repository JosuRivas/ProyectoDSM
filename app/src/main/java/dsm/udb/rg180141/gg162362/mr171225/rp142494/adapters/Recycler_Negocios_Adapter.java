package dsm.udb.rg180141.gg162362.mr171225.rp142494.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import dsm.udb.rg180141.gg162362.mr171225.rp142494.R;
import dsm.udb.rg180141.gg162362.mr171225.rp142494.modelos.Negocio;

public class Recycler_Negocios_Adapter extends RecyclerView.Adapter<Recycler_Negocios_Adapter.View_Holder_Negocio> implements Filterable {
    Context contexto;
    ArrayList<Negocio> listaNegocios;
    ArrayList<Negocio> listaNegociosEntera;
    private OnItemListener mOnItemListener;

    public Recycler_Negocios_Adapter(Context contexto, ArrayList<Negocio> listaNegocios,OnItemListener onItemListener) {
        this.contexto = contexto;
        this.listaNegocios = listaNegocios;
        this.listaNegociosEntera = new ArrayList<Negocio>(listaNegocios);
        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public Recycler_Negocios_Adapter.View_Holder_Negocio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(contexto).inflate(R.layout.item_lista_negocios,parent,false);
        return new View_Holder_Negocio(view,mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_Negocios_Adapter.View_Holder_Negocio holder, int position) {
        Negocio negocio = listaNegocios.get(position);
        holder.TVNombreNegocio.setText(negocio.getNombre());
        holder.TVDepartamento.setText(negocio.getDepartamento());
        holder.TVMunicipio.setText(negocio.getMunicipio());
        holder.TVId.setText(negocio.getId());
    }

    @Override
    public int getItemCount() {
        return listaNegocios.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<Negocio> listaFiltrada = new ArrayList<Negocio>();
            if (charSequence == null || charSequence.length() == 0){
                listaFiltrada.addAll(listaNegociosEntera);
            } else {
                for (Negocio negocio : listaNegociosEntera) {
                    if (negocio.getDepartamento().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        listaFiltrada.add(negocio);
                    }
                }
            }
            FilterResults resultadosFiltrados = new FilterResults();
            resultadosFiltrados.values = listaFiltrada;
            return resultadosFiltrados;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults resultadosFiltrados) {
            listaNegocios.clear();
            listaNegocios.addAll((ArrayList<Negocio>) resultadosFiltrados.values);
            notifyDataSetChanged();
        }
    };

    public class View_Holder_Negocio extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView TVNombreNegocio,TVDepartamento,TVMunicipio,TVId;
        OnItemListener onItemListener;

        public View_Holder_Negocio(@NonNull View itemView,OnItemListener onItemListener) {
            super(itemView);

            TVNombreNegocio = (TextView)itemView.findViewById(R.id.TVNombreNegocio);
            TVDepartamento = (TextView)itemView.findViewById(R.id.TVDepartamento);
            TVMunicipio = (TextView)itemView.findViewById(R.id.TVMunicipio);
            TVId = (TextView) itemView.findViewById(R.id.TVIdentificador);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener{
        void onItemClick(int posicion);
    }
}
