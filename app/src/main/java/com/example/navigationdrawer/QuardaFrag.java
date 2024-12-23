package com.example.navigationdrawer;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuardaFrag extends Fragment {

    public QuardaFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quarda, container, false);

        Button solveButton = view.findViewById(R.id.buttonSolveQuadratic);
        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveQuadraticEquation(v);
            }
        });

        return view;
    }

    // Solve quadratic equation
    public void solveQuadraticEquation(View view) {
        EditText editTextQuadraticA = getView().findViewById(R.id.editTextQuadraticA);
        EditText editTextQuadraticB = getView().findViewById(R.id.editTextQuadraticB);
        EditText editTextQuadraticC = getView().findViewById(R.id.editTextQuadraticC);
        TextView textViewResultQuadratic = getView().findViewById(R.id.textViewResultQuadratic);

        String quadraticA = editTextQuadraticA.getText().toString();
        String quadraticB = editTextQuadraticB.getText().toString();
        String quadraticC = editTextQuadraticC.getText().toString();

        // Check if any of the EditText fields is empty
        if (quadraticA.isEmpty() || quadraticB.isEmpty() || quadraticC.isEmpty()) {
            textViewResultQuadratic.setText("Please enter all coefficients (a, b, and c).");
            return;
        }

            // Check if 'a' is zero
            if ( Float.parseFloat(quadraticA) == 0) {
                textViewResultQuadratic.setText("Coefficient (a) cannot be zero. Please enter a non-zero value.");
                return;
            }
        // Solve quadratic equation
        Object result = solveQuadratic(
                Float.parseFloat(quadraticA),
                Float.parseFloat(quadraticB),
                Float.parseFloat(quadraticC)
        );

        // Display the result for quadratic equation
        String resultQuadratic = formatResult(result);
        textViewResultQuadratic.setText(resultQuadratic);
    }

    // Function to solve quadratic equation
    private Object solveQuadratic(float a, float b, float c) {
        float delta = b * b - 4 * a * c;

        if (delta > 0) {
            float x1 = (float) ((-b + Math.sqrt(delta)) / (2 * a));
            float x2 = (float) ((-b - Math.sqrt(delta)) / (2 * a));
            return new float[]{x1, x2};
        } else if (delta == 0) {
            float x1 = -b / (2 * a);
            return new float[]{x1, x1};
        } else {
            // Calculate imaginary roots
            float realPart = -b / (2 * a);
            float imaginaryPart = (float) Math.sqrt(Math.abs(delta)) / (2 * a);
            return new Complex(realPart, imaginaryPart);
        }
    }

    // Format the result for display
    private String formatResult(Object result) {
        if (result instanceof float[]) {
            float[] roots = (float[]) result;
            return "Root 1: " + roots[0] + "\nRoot 2: " + roots[1];
        } else if (result instanceof Complex) {
            Complex complexRoot = (Complex) result;
            return "Root 1: " +complexRoot + "\nRoot 2: " + complexRoot.conjugate();
        } else {
            return "No real roots";
        }
    }

    // Complex number class
    private static class Complex {
        private final float real;
        private final float imaginary;

        public Complex(float real, float imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        public String toString() {
            return real + (imaginary >= 0 ? " + " : " - ") + Math.abs(imaginary) + "i";
        }

        public Complex conjugate() {
            return new Complex(real, -imaginary);
        }
    }
}
