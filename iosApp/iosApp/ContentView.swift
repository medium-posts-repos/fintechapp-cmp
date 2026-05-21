import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(nativeViewFactory: IOSNativeViewFactory.shared)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        MainTabView()
            .background(.white)
            .ignoresSafeArea(.keyboard)
    }
}

import SwiftUI

struct RippleButton: View {
    @State private var rippleScale: CGFloat = 0.1
    @State private var rippleOpacity: Double = 1.0
    @State private var showRipple = false

    var body: some View {
        ZStack {
            Button(action: {
                triggerRipple()
                // Handle your action here
            }) {
                Text("Tap Me")
                    .font(.headline)
                    .foregroundColor(.white)
                    .padding()
                    .background(Circle().fill(Color.blue))
            }

            if showRipple {
                Circle()
                    .fill(Color.white.opacity(0.4))
                    .frame(width: 100, height: 100)
                    .scaleEffect(rippleScale)
                    .opacity(rippleOpacity)
                    .animation(.easeOut(duration: 0.5), value: rippleScale)
            }
        }
    }

    private func triggerRipple() {
        rippleScale = 0.1
        rippleOpacity = 1.0
        showRipple = true

        withAnimation {
            rippleScale = 2.5
            rippleOpacity = 0.0
        }

        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            showRipple = false
        }
    }
}

struct RippleButton_Previews: PreviewProvider {
    static var previews: some View {
        RippleButton()
    }
}
