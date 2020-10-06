using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading.Tasks;
using Windows.ApplicationModel;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.System;
using Windows.UI.Core.Preview;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// The Blank Page item template is documented at https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0x409

namespace App1
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {
        DispatcherTimer dispatcherTimer = new DispatcherTimer();
        Stopwatch stopWatch = new Stopwatch();
        string currentTime = string.Empty;

        public MainPage()
        {
            
            this.InitializeComponent();
            SystemNavigationManagerPreview.GetForCurrentView().CloseRequested += CloseHandle;
            dispatcherTimer.Tick += new EventHandler<object>(dt_Tick);
            dispatcherTimer.Interval = new TimeSpan(0, 0, 0, 1);
            stopWatch.Start();
            dispatcherTimer.Start();
        }
        private async void CloseHandle(object sender, SystemNavigationCloseRequestedPreviewEventArgs e)
        {
            
            e.Handled = true;
            IList<AppDiagnosticInfo> infos = await AppDiagnosticInfo.RequestInfoForAppAsync();
            IList<AppResourceGroupInfo> resourceInfos = infos[0].GetResourceGroups();
            await resourceInfos[0].StartSuspendAsync();
        }
        public async Task<StartupTask> ReadState()
        {
            var state = await StartupTask.GetAsync("MyStartupId");
            return state;
        }
        void dt_Tick(object sender, object e)
        {
            TimeSpan ts = stopWatch.Elapsed;
            currentTime = String.Format("{0:00}:{1:00}:{2:00}",
            ts.Hours, ts.Minutes, ts.Seconds);
            durationSession.Text = currentTime;
        }

        private async void txtStatus_Click(object sender, RoutedEventArgs e)
        {
            var stateMode = await ReadState();

            switch (stateMode.State)
            {
                case StartupTaskState.Disabled:
                    break;
                case StartupTaskState.DisabledByUser:
                    break;
                case StartupTaskState.Enabled:
                    break;
                case StartupTaskState.DisabledByPolicy:
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }
    }
}
            