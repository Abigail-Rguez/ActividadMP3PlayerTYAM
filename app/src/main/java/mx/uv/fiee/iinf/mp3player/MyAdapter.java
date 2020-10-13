package mx.uv.fiee.iinf.mp3player;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptador personalizado para controlar el llenado de datos del recyclerview
 */
class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<AudioModel> data;
    private OnAudioSelectedListener listener;

    public MyAdapter (Context context, List<AudioModel> data) {
        this.data = data;
        this.context = context;
    }

    /**
     * Manajador para el evento de selección de elemento en la lista
     * @param listener objeto que implementa la interfaz OnAudioSelectedListener
     */
    public void setOnAudioSelectedListener (OnAudioSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.list_item, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position) {
        String foo = data.get (position).name;
        holder.text1.setText (foo);

        holder.itemView.setOnClickListener (v -> {
            Uri contentUri = ContentUris.withAppendedId (
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    data.get (position).id
            );

            listener.audioSelected (contentUri);
        });
    }

    @Override
    public int getItemCount () {
        return data.size ();
    }


    /**
     * Mantiene referencia al componente que interesa reutilizar en la vista
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById (R.id.tvItem);
        }
    }

}
