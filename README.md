# FYP_Object_Detection

## Project Overview
This project is an object detection system that leverages the SSD MobileNet V3 model to detect objects in images and videos. It includes a Python-based API for model inference and a Java-based web application for user interaction.

## Project Structure
```
FYP_Object_detection/
│
├── model-api/
│   ├── coco.names                      # List of COCO dataset class names
│   ├── frozen_inference_graph.pb       # Pre-trained TensorFlow model
│   ├── haarcascade_frontalface_default.xml # Haarcascade model for face detection
│   ├── lena.png                        # Sample image for testing
│   ├── main.py                         # Main script for running the object detection API
│   ├── objectdetector.py               # Object detection logic
│   ├── output21.mp4                    # Sample output video
│   ├── requirements.txt                # Python dependencies
│   ├── ssd_mobilenet_v3_large_coco_2020_01_14.pbtxt # Model configuration file
│
├── object-detection-webapp/
│   ├── HELP.md                         # Help documentation for the web app
│   ├── mvnw                            # Maven wrapper script
│   ├── mvnw.cmd                        # Maven wrapper script for Windows
│   ├── pom.xml                         # Maven project configuration
│   ├── src/                            # Source code for the web application
│       ├── main/
│           ├── java/
│               ├── com/example/objectdetectionwebapp/
│                   ├── ObjectDetectionWebappApplication.java # Main application class
│                   ├── config/
│                       ├── SecurityConfig.java # Security configuration
│                   ├── controller/
│                       ├── DetectionController.java # Controller for detection endpoints
│                   ├── model/
│                       ├── User.java # User model
│                   ├── repository/
│                       ├── UserRepository.java # User repository
│                   ├── service/
│                       ├── UserService.java # User service logic
│           ├── resources/
│               ├── application.properties # Application configuration
│               ├── static/
│                   ├── css/
│                       ├── styles.css # CSS styles
│               ├── templates/
│                   ├── about.html     # About page
│                   ├── index.html     # Home page
│                   ├── login.html     # Login page
│                   ├── register.html  # Registration page
│       ├── test/
│           ├── java/
│               ├── com/example/objectdetectionwebapp/
│                   ├── ObjectDetectionWebappApplicationTests.java # Unit tests
│
├── README.md                           # Project documentation
```

## Prerequisites

### Python API
- Python 3.8 or higher
- Install dependencies using `pip install -r requirements.txt`

### Java Web Application
- Java 11 or higher
- Maven 3.6 or higher

## Setup Instructions

### Python API
1. Navigate to the `model-api` directory:
   ```bash
   cd model-api
   ```
2. Install the required Python packages:
   ```bash
   pip install -r requirements.txt
   ```
3. Run the API:
   ```bash
   python main.py
   ```

### Java Web Application
1. Navigate to the `object-detection-webapp` directory:
   ```bash
   cd object-detection-webapp
   ```
2. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Features
- **Object Detection API**: Detect objects in images and videos using SSD MobileNet V3.
- **Web Application**: User-friendly interface for uploading images/videos and viewing detection results.
- **User Management**: Secure login and registration system.

## Usage
1. Start the Python API and the Java web application.
2. Access the web application at `http://localhost:8080`.
3. Upload an image or video for object detection.
4. View the detection results on the web interface.

## Contributing
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push the branch.
4. Submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
