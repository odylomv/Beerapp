package com.example.beerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * This class is used to create the modal dialog where the user can write o modify a comment for a beer
 */
public class ModalForComment extends AppCompatDialogFragment {

    private EditText editTextComment;
    private ModalCommentListener listener;
    private final int beerId;

    public ModalForComment(int beerId) {
        this.beerId = beerId;
    }

    @Override @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.modal_for_comment,null);
        Utilities utilities = new Utilities(getActivity());

        String commentState="";
        String displayOnPositiveButton; //either new or modify, text for the button
        editTextComment = view.findViewById(R.id.editTextComment);
        if(utilities.getCommented().contains(beerId)){ //if it has comment then we modify we don't add
            displayOnPositiveButton = "Modify comment";
            commentState = "EXISTING";
            try {
                editTextComment.setText(utilities.getComment(beerId), TextView.BufferType.EDITABLE);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else{ //new comment
            displayOnPositiveButton = "Add comment";
            commentState = "NEW";
        }

        String finalCommentState = commentState;
        builder.setView(view)
                .setTitle("Comment")
                .setNegativeButton("cancel", (dialogInterface, i) -> { })
                .setPositiveButton(displayOnPositiveButton, (dialogInterface, i) -> {
                    String comment = editTextComment.getText().toString().trim();
                    listener.applyTexts(comment,finalCommentState);
                });

        return builder.create();
    }

    public interface ModalCommentListener {
        void applyTexts(String comment,String state);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ModalCommentListener) context; //IMPORTANT if not impemented on Beer Activity modal wont work properly
    }
}