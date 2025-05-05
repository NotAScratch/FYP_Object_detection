from fastapi import FastAPI
from fastapi.responses import StreamingResponse
from objectdetector import process_frame, initialize_camera, release_camera
import uvicorn

app = FastAPI()

# Initialize the camera when the app starts
@app.on_event("startup")
async def startup_event():
    initialize_camera()

# Release the camera when the app shuts down
@app.on_event("shutdown")
async def shutdown_event():
    release_camera()

# Video feed streaming endpoint
@app.get("/video_feed")
async def video_feed(width: int = 640, height: int = 480):
    def generate():
        while True:
            frame_bytes, _ = process_frame()
            if frame_bytes is None:
                continue
            # Resize the frame (if applicable)
            yield (b'--frame\r\n'
                   b'Content-Type: image/jpeg\r\n\r\n' + frame_bytes + b'\r\n')

    return StreamingResponse(generate(), media_type="multipart/x-mixed-replace; boundary=frame")
# Detections endpoint
# filepath: f:\JAVA\model-api\main.py
import logging

logging.basicConfig(level=logging.INFO)

@app.get("/detections")
async def get_detections():
    try:
        _, detections = process_frame()
        logging.info(f"Detections: {detections}")
        return detections
    except Exception as e:
        logging.error(f"Error in /detections: {e}")
        return {"error": str(e)}, 500