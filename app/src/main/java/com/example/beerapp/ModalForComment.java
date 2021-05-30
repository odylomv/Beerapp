package com.example.beerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

public class ModalForComment extends AppCompatDialogFragment {

    private EditText editTextComment;
    private ModalCommentListener listener;
    private int beerId;

    public ModalForComment(int beerId) {
        this.beerId = beerId;
    }

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.modal_for_comment,null);
        Utilities utilities = new Utilities(getActivity());

        String displayOnPositiveButton = "";
        if(utilities.getCommented().contains(beerId)){
            displayOnPositiveButton = "Modify comment";
        }
        else{
            displayOnPositiveButton = "Add comment";
        }

        builder.setView(view)
                .setTitle("Comment")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(displayOnPositiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String comment = editTextComment.getText().toString().trim();
                        listener.applyTexts(comment);
                    }
                });
        editTextComment = view.findViewById(R.id.editTextComment);

        return builder.create();

    }
    public interface ModalCommentListener{
        void applyTexts(String comment);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        listener = (ModalCommentListener) context; //IMPORTANT if not impemented on Beer Activity rip
    }
}
