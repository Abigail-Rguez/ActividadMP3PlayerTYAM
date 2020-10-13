package mx.uv.fiee.iinf.mp3player;

import android.net.Uri;

/**
 * Interfaz que define al objeto manajador del evento click en algun elemento de la lista
 */
interface OnAudioSelectedListener {
    void audioSelected (Uri item);
}
