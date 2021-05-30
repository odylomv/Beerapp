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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ModalForComment extends AppCompatDialogFragment {

    private EditText editTextComment;
    private ModalCommentListener listener;
    private int beerId;

    public ModalForComment(int beerId) {
        this.beerId = beerId;
    }

    @Override @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.modal_for_comment,null);
        Utilities utilities = new Utilities(getActivity());

        String displayOnPositiveButton;
        String commentstate = "";
        editTextComment = view.findViewById(R.id.editTextComment);
        if(utilities.getCommented().contains(beerId)){
            displayOnPositiveButton = "Modify comment";
            commentstate = "EXISTING";
            try {
                editTextComment.setText(utilities.getComment(beerId), TextView.BufferType.EDITABLE);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else{
            displayOnPositiveButton = "Add comment";
            commentstate = "NEW";
        }

        String finalCommentstate = commentstate;
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
                        listener.applyTexts(comment,finalCommentstate);
                    }
                });


        return builder.create();

    }
    public interface ModalCommentListener{
        void applyTexts(String comment,String state);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ModalCommentListener) context; //IMPORTANT if not impemented on Beer Activity rip
    }
}
