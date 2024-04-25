///////////////////////////////////////////////////////////////////////////////
// viewmanager.h
// ============
// manage the viewing of 3D objects within the viewport
//
//  AUTHOR: Brian Battersby - SNHU Instructor / Computer Science
//	Created for CS-330-Computational Graphics and Visualization, Nov. 1st, 2023
///////////////////////////////////////////////////////////////////////////////

#pragma once

#include "ShaderManager.h"
#include "Camera.h" // Ensure this is the correct path to your Camera class

// GLFW library
#include "GLFW/glfw3.h"

class ViewManager {
public:
    // Constructor
    ViewManager(ShaderManager* pShaderManager);
    // Destructor
    ~ViewManager();

    // Callbacks for mouse interaction
    static void Mouse_Position_Callback(GLFWwindow* window, double xMousePos, double yMousePos);
    static void Mouse_Scroll_Callback(GLFWwindow* window, double xoffset, double yoffset);

    // Static member for camera speed adjustment
    static float cameraSpeedMultiplier;

    // Create the OpenGL display window
    GLFWwindow* CreateDisplayWindow(const char* windowTitle);

    // Prepare the scene view
    void PrepareSceneView();

    void ToggleProjectionMode(); // Function to toggle projection mode

private:
    ShaderManager* m_pShaderManager; // Shader manager object
    GLFWwindow* m_pWindow; // Active OpenGL display window

    void ProcessKeyboardEvents(); // Process keyboard events for 3D scene interaction

    bool usePerspective = true; // True for perspective, false for orthographic
};
