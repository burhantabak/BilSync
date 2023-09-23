import "./navbar.css"
export default function Navbar(){
    return (<div className="navbar">
        <div className="navbar-left">
            <img src="/bilkentlogo.png" alt="logo" />
            <h3 className="brand-title">CampusConnect</h3>
        </div>
        <div className="navbar-right">
        <ul>
            <li>About us</li>
            <li> Sign Up</li>
        </ul>
        </div>
        </div>);
}