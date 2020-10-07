#type vertex
#version 330 core

layout(location=0) in vec4 pos;
layout(location=1) in vec2 uv_;


out vec2 uv;
out vec2 wpos;
uniform mat4 mvp;

void main() {
    gl_Position = mvp * pos;
    uv = uv_;
    wpos = pos.xy;
}