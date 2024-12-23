package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class linearFrag3 extends Fragment {

    private EditText l3CoefficientA1, l3CoefficientB1, l3CoefficientC1, l3ConstantD1;
    private EditText l3CoefficientA2, l3CoefficientB2, l3CoefficientC2, l3ConstantD2;
    private EditText l3CoefficientA3, l3CoefficientB3, l3CoefficientC3, l3ConstantD3;
    private Button buttonSolve;
    private TextView textViewResult;

    public linearFrag3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_linear_frag3, container, false);

        // Initialize UI components
        l3CoefficientA1 = rootView.findViewById(R.id.l3CoefficientA1);
        l3CoefficientB1 = rootView.findViewById(R.id.l3CoefficientB1);
        l3CoefficientC1 = rootView.findViewById(R.id.l3CoefficientC1);
        l3ConstantD1 = rootView.findViewById(R.id.l3ConstantD1);

        l3CoefficientA2 = rootView.findViewById(R.id.l3CoefficientA2);
        l3CoefficientB2 = rootView.findViewById(R.id.l3CoefficientB2);
        l3CoefficientC2 = rootView.findViewById(R.id.l3CoefficientC2);
        l3ConstantD2 = rootView.findViewById(R.id.l3ConstantD2);

        l3CoefficientA3 = rootView.findViewById(R.id.l3CoefficientA3);
        l3CoefficientB3 = rootView.findViewById(R.id.l3CoefficientB3);
        l3CoefficientC3 = rootView.findViewById(R.id.l3CoefficientC3);
        l3ConstantD3 = rootView.findViewById(R.id.l3ConstantD3);

        buttonSolve = rootView.findViewById(R.id.buttonSolve);
        textViewResult = rootView.findViewById(R.id.textViewResult);

        // Set onClickListener for the Solve button
        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solveSystemOfEquations();
            }
        });

        return rootView;
    }

    private void solveSystemOfEquations() {
        // Get the coefficients and constants entered by the user
        String a1Str = l3CoefficientA1.getText().toString();
        String b1Str = l3CoefficientB1.getText().toString();
        String c1Str = l3CoefficientC1.getText().toString();
        String d1Str = l3ConstantD1.getText().toString();
        String a2Str = l3CoefficientA2.getText().toString();
        String b2Str = l3CoefficientB2.getText().toString();
        String c2Str = l3CoefficientC2.getText().toString();
        String d2Str = l3ConstantD2.getText().toString();
        String a3Str = l3CoefficientA3.getText().toString();
        String b3Str = l3CoefficientB3.getText().toString();
        String c3Str = l3CoefficientC3.getText().toString();
        String d3Str = l3ConstantD3.getText().toString();

        if (a1Str.isEmpty() || b1Str.isEmpty() || c1Str.isEmpty() || d1Str.isEmpty() ||
                a2Str.isEmpty() || b2Str.isEmpty() || c2Str.isEmpty() || d2Str.isEmpty() ||
                a3Str.isEmpty() || b3Str.isEmpty() || c3Str.isEmpty() || d3Str.isEmpty()) {
            textViewResult.setText("Please enter all coefficients and constants.");
            return;
        }

        // Parse the coefficients and constants as doubles
        double a1 = Double.parseDouble(a1Str);
        double b1 = Double.parseDouble(b1Str);
        double c1 = Double.parseDouble(c1Str);
        double d1 = Double.parseDouble(d1Str);
        double a2 = Double.parseDouble(a2Str);
        double b2 = Double.parseDouble(b2Str);
        double c2 = Double.parseDouble(c2Str);
        double d2 = Double.parseDouble(d2Str);
        double a3 = Double.parseDouble(a3Str);
        double b3 = Double.parseDouble(b3Str);
        double c3 = Double.parseDouble(c3Str);
        double d3 = Double.parseDouble(d3Str);

        // Solve the system of linear equations
        double determinant = a1 * (b2 * c3 - c2 * b3) - b1 * (a2 * c3 - c2 * a3) + c1 * (a2 * b3 - b2 * a3);

        if (determinant == 0) {
            textViewResult.setText("The system of equations has no unique solution.");
        } else {
            double x = (d1 * (b2 * c3 - c2 * b3) - b1 * (d2 * c3 - c2 * d3) + c1 * (d2 * b3 - b2 * d3)) / determinant;
            double y = (a1 * (d2 * c3 - c2 * d3) - d1 * (a2 * c3 - c2 * a3) + c1 * (a2 * d3 - d2 * a3)) / determinant;
            double z = (a1 * (b2 * d3 - d2 * b3) - b1 * (a2 * d3 - d2 * a3) + d1 * (a2 * b3 - b2 * a3)) / determinant;


            // Display the result
            String result = String.format("Solution: x = %.4f, y = %.4f, z = %.4f", x, y, z);

            // Display the formatted result
            textViewResult.setText(result);
        }
    }
}
