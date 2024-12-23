package com.example.navigationdrawer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class linearFrag2 extends Fragment {

    private EditText l2CoefficientA1, l2CoefficientB1, l2ConstantC1;
    private EditText l2CoefficientA2, l2CoefficientB2, l2ConstantC2;
    private Button buttonSolve;
    private TextView textViewResult;

    public linearFrag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_linear_frag2, container, false);

        // Initialize UI components
        l2CoefficientA1 = rootView.findViewById(R.id.l2CoefficientA1);
        l2CoefficientB1 = rootView.findViewById(R.id.l2CoefficientB1);
        l2ConstantC1 = rootView.findViewById(R.id.l2CoefficientC1);
        l2CoefficientA2 = rootView.findViewById(R.id.l2CoefficientA2);
        l2CoefficientB2 = rootView.findViewById(R.id.l2CoefficientB2);
        l2ConstantC2 = rootView.findViewById(R.id.l2CoefficientC2);
        buttonSolve = rootView.findViewById(R.id.buttonSolve);
        textViewResult = rootView.findViewById(R.id.textViewResult);

        // Set onClickListener for the Solve button
        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solveLinearEquationSystem();
            }
        });

        return rootView;
    }

    private void solveLinearEquationSystem() {
        // Get the coefficients and constants entered by the user
        String a1Str = l2CoefficientA1.getText().toString();
        String b1Str = l2CoefficientB1.getText().toString();
        String c1Str = l2ConstantC1.getText().toString();
        String a2Str = l2CoefficientA2.getText().toString();
        String b2Str = l2CoefficientB2.getText().toString();
        String c2Str = l2ConstantC2.getText().toString();

        if (a1Str.isEmpty() || b1Str.isEmpty() || c1Str.isEmpty() ||
                a2Str.isEmpty() || b2Str.isEmpty() || c2Str.isEmpty()) {
            textViewResult.setText("Please enter all coefficients and constants.");
            return;
        }

        // Parse the coefficients and constants as doubles
        double a1 = Double.parseDouble(a1Str);
        double b1 = Double.parseDouble(b1Str);
        double c1 = Double.parseDouble(c1Str);
        double a2 = Double.parseDouble(a2Str);
        double b2 = Double.parseDouble(b2Str);
        double c2 = Double.parseDouble(c2Str);

        // Solve the system of linear equations
        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            textViewResult.setText("The system of equations has no unique solution.");
        } else {
            double x = (c1 * b2 - c2 * b1) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;

            // Display the result
            String result = String.format("Solution: x = %.4f, y = %.4f", x, y);

            // Display the formatted result
            textViewResult.setText(result);
        }
    }
}
