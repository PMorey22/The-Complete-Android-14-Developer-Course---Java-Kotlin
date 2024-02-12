package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class ML_model : AppCompatActivity() {

    private lateinit var interpreter: Interpreter

    private val NUM_CLASSES = 5 // Update this value with the number of taste categories in your model
    private val labelMap = mapOf(
        0 to "Sweet",
        1 to "Bitter",
        2 to "Sour",
        3 to "Astringent",
        4 to "Pungent"  )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ml_model)
        interpreter = Interpreter(loadModelFile())

        val predictButton: Button = findViewById(R.id.button)
        val outputTextView:TextView=findViewById(R.id.output)
        val glucose:EditText=findViewById(R.id.Glucose)
        val Sucrose:EditText=findViewById(R.id.Sucrose)
        val Fructose:EditText=findViewById(R.id.Fructose)
        val Tannins:EditText=findViewById(R.id.Tannins)
        val Phenolic_Acid:EditText=findViewById(R.id.Phenolic_Acid)
        val Citric_Acid:EditText=findViewById(R.id.Citriic_Acid)
        val Malic_Acid:EditText=findViewById(R.id.Malic)
        val Tartaric_Acid:EditText=findViewById(R.id.tartaric)
        val Alkaloids:EditText=findViewById(R.id.Alcaloids)
        val Terepens:EditText=findViewById(R.id.terepens)

        predictButton.setOnClickListener {
            // Get user input values
            val glucose = glucose.text.toString().toFloat()
            val sucrose = Sucrose.text.toString().toFloat()
            val Fructose = Fructose.text.toString().toFloat()
            val Tannins = Tannins.text.toString().toFloat()
            val Phenolic_Acid = Phenolic_Acid.text.toString().toFloat()
            val Citric_Acid = Citric_Acid.text.toString().toFloat()
            val Malic_Acid = Malic_Acid.text.toString().toFloat()
            val Tartaric_Acid = Tartaric_Acid.text.toString().toFloat()
            val Alkaloids = Alkaloids.text.toString().toFloat()
            val Terepens = Terepens.text.toString().toFloat()

            val inputArray = arrayOf(floatArrayOf(glucose, sucrose, Fructose,Tannins,Phenolic_Acid,Citric_Acid,Malic_Acid,Tartaric_Acid,Alkaloids,Terepens))
            val outputArray = Array(1) { FloatArray(NUM_CLASSES) }
            interpreter.run(inputArray, outputArray)

            val predictedClass = outputArray[0].indexOf(outputArray[0].maxOrNull())
            val predictedTaste = labelMap[predictedClass] ?: "Unknown" // labelMap is a map to map the index to taste category

            // Display the predicted taste category
            outputTextView.text="Predicted Taste: $predictedTaste"
        }
    }

    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = assets.openFd("model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}

private fun Any.indexOf(maxOrNull: Float?): Int {
    if (this is FloatArray && maxOrNull != null) {
        return this.indexOf(maxOrNull)
    }
    return -1 // or throw an exception for invalid types or null values
}


