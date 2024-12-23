package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class linearFrag1 extends Fragment {

    private EditText editTextCoefficientA;
    private EditText editTextCoefficientB;
    private Button buttonSolve;
    private TextView textViewResult;

    public linearFrag1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_linear_frag1, container, false);

        // Initialize UI components
        editTextCoefficientA = rootView.findViewById(R.id.editTextCoefficientA);
        editTextCoefficientB = rootView.findViewById(R.id.editTextCoefficientB);
        buttonSolve = rootView.findViewById(R.id.buttonSolve);
        textViewResult = rootView.findViewById(R.id.textViewResult);

        // Set onClickListener for the Solve button
        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solveLinearEquation();
            }
        });
        return rootView;
    }

    private void solveLinearEquation() {
        // Get the coefficients entered by the user
        String coefficientAStr = editTextCoefficientA.getText().toString();
        String coefficientBStr = editTextCoefficientB.getText().toString();

        if (coefficientAStr.isEmpty() || coefficientBStr.isEmpty()) {
            textViewResult.setText("Please enter both coefficients (a and b).");
            return;
        }

        // Parse the coefficients as doubles
        double coefficientA = Double.parseDouble(coefficientAStr);
        double coefficientB = Double.parseDouble(coefficientBStr);

        // Solve the linear equation (ax + b = 0)
        double solution = -coefficientB / coefficientA;

        // Display the result
        String result = String.format("The Solution is: x = %.4f", solution);

        // Display the formatted result
        textViewResult.setText(result);
    }
}
