package org.renaissance.bytedeco.openncv;

import org.renaissance.Benchmark;
import org.renaissance.BenchmarkContext;
import org.renaissance.BenchmarkResult;
import org.renaissance.BenchmarkResult.Validators;
import org.renaissance.License;

import static java.util.Objects.requireNonNull;
import static org.renaissance.Benchmark.*;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_imgproc.*;
import org.bytedeco.javacv.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.indexer.*;
import org.bytedeco.opencv.opencv_calib3d.*;
import org.bytedeco.opencv.opencv_objdetect.*;
import static org.bytedeco.opencv.global.opencv_calib3d.*;
import static org.bytedeco.opencv.global.opencv_objdetect.*;
import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;


@Name("face-detection")
@Group("bytedeco-opencv")
@Summary("A benchmark that detects faces from an input image.")
@Licenses(License.APACHE2)
@Repetitions(100)
@Configuration(name = "test")
public final class FaceDetection implements Benchmark
{
  private Mat image;
  private CascadeClassifier classifier;

  @Override
  public void setUpBeforeAll(BenchmarkContext context)
  {
    String imagePath = "benchmarks/bytedeco-opencv/src/main/resources/in.jpg";
    image = imread(imagePath);

    String classifierName = "benchmarks/bytedeco-opencv/src/main/resources/haarcascade_frontalface_alt.xml";
    classifier = new CascadeClassifier(classifierName);

    if (classifier == null)
    {
      System.err.println("Error loading classifier file \"" + classifierName + "\".");
      System.exit(1);
    }
  }

  @Override
  public BenchmarkResult run(BenchmarkContext c)
  {
    // Detect the faces
    RectVector faceDetections = new RectVector();
    classifier.detectMultiScale(image, faceDetections);
    return Validators.simple("The number of recognized faces", 3, faceDetections.size());
  }
}