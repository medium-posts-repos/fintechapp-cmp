import SwiftUI
import ComposeApp

struct MainTabView: View {
    @State private var selectedTab = 0

    var body: some View {
        TabView(selection: $selectedTab) {
            ComposeViewControllerWrapper { ViewControllerFactory.shared.HomeViewController() }
                .ignoresSafeArea(edges: .top)
                .tabItem {
                    Label("Home", systemImage: "house")
                }
                .tag(0)
            
            ComposeViewControllerWrapper { ViewControllerFactory.shared.PaymentsViewController() }
                .ignoresSafeArea(edges: .top)
                .tabItem {
                    Label("Payments", systemImage: "cart")
                }
                .tag(1)
            
            ComposeViewControllerWrapper { ViewControllerFactory.shared.CameraViewController() }
                .ignoresSafeArea(edges: .top)
                .tabItem {
                    Label("Scan", systemImage: "qrcode.viewfinder")
                }
                .tag(2)
            
            ComposeViewControllerWrapper { ViewControllerFactory.shared.TransfersViewController() }
                .ignoresSafeArea(edges: .top)
                .tabItem {
                    Label("Transfers", systemImage: "paperplane")
                }
                .tag(3)
            
            ComposeViewControllerWrapper { ViewControllerFactory.shared.ProfileViewController() }
                .ignoresSafeArea(edges: .top)
                .tabItem {
                    Label("Profile", systemImage: "person")
                }
                .tag(4)
        }
    }
}

struct ComposeViewControllerWrapper: UIViewControllerRepresentable {
    let makeVC: () -> UIViewController
    
    func makeUIViewController(context: Context) -> UIViewController {
        return makeVC()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
