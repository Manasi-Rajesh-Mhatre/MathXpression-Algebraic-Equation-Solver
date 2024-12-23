package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;

public class CubicFrag extends Fragment {

    public CubicFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cubic, container, false);

        Button solveButton = view.findViewById(R.id.buttonSolveCubic);
        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveCubicEquation(view);
            }
        });

        return view;
    }

    // Solve cubic equation
    public void solveCubicEquation(View view) {
        EditText editTextA = view.findViewById(R.id.editTextA);
        EditText editTextB = view.findViewById(R.id.editTextB);
        EditText editTextC = view.findViewById(R.id.editTextC);
        EditText editTextD = view.findViewById(R.id.editTextD);
        TextView textViewResultCubic = view.findViewById(R.id.textViewResultCubic);

        String coefficientA = editTextA.getText().toString();
        String coefficientB = editTextB.getText().toString();
        String coefficientC = editTextC.getText().toString();
        String coefficientD = editTextD.getText().toString();

        // Check if any of the EditText fields is empty
        if (coefficientA.isEmpty() || coefficientB.isEmpty() || coefficientC.isEmpty()) {
            textViewResultCubic.setText("Please enter all coefficients (a, b, and c).");
            return;
        }

        // Check if 'a' is zero
        if ( Float.parseFloat(coefficientA) == 0) {
            textViewResultCubic.setText("Coefficient (a) cannot be zero. Please enter a non-zero value.");
            return;
        }

        // Check if coefficients are valid numbers
        try {
            Double.parseDouble(coefficientA);
            Double.parseDouble(coefficientB);
            Double.parseDouble(coefficientC);
            Double.parseDouble(coefficientD);
        } catch (NumberFormatException e) {
            textViewResultCubic.setText("Please enter valid numeric coefficients.");
            return;
        }

        double a = Double.parseDouble(coefficientA);
        double b = Double.parseDouble(coefficientB);
        double c = Double.parseDouble(coefficientC);
        double d = Double.parseDouble(coefficientD);

        // Cardano's method
        double p = (3 * a * c - b * b) / (3 * a * a);
        double q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a * a);
        double delta = q * q / 4 + p * p * p / 27;

        if (delta > 0) {
            // One real root and two complex conjugate roots
            double u = Math.cbrt(-q / 2 + Math.sqrt(delta));
            double v = Math.cbrt(-q / 2 - Math.sqrt(delta));

            double root1 = u + v - b / (3 * a);
            double root2 = -0.5 * (u + v) - b / (3 * a);
            double root3 = 0.5 * Math.sqrt(3) * (u - v);

            textViewResultCubic.setText(String.format("Root 1: %.4f, Root 2: %.4f + %.4fi, Root 3: %.4f - %.4fi", root1, root2, root3, root2, root3));
        } else if (delta == 0) {
            // Three real roots, two of them are repeated
            double u = Math.cbrt(-q / 2);

            double root1 = -u - b / (3 * a);
            double root2 = root1;
            double root3 = 2 * u - b / (3 * a);

            textViewResultCubic.setText(String.format("Root 1: %.4f, Root 2: %.4f, Root 3: %.4f", root1, root2, root3));
        } else {
            // Three real and distinct roots
            double r = Math.sqrt(-p / 3);
            double theta = Math.acos(-q / (2 * r * r * r));
            double root1 = 2 * r * Math.cos(theta / 3) - b / (3 * a);
            double root2 = 2 * r * Math.cos((theta + 2 * Math.PI) / 3) - b / (3 * a);
            double root3 = 2 * r * Math.cos((theta + 4 * Math.PI) / 3) - b / (3 * a);


            // Ensure correct ordering of roots
            double[] sortedRoots = {root1, root2, root3};
            Arrays.sort(sortedRoots);

            root1 = sortedRoots[0];
            root2 = sortedRoots[1];
            root3 = sortedRoots[2];

            textViewResultCubic.setText(String.format("Root 1: %.4f, Root 2: %.4f, Root 3: %.4f", root1, root2, root3));
        }

        if(textViewResultCubic == null){
            textViewResultCubic.setText("No Roots");
        }
    }
}